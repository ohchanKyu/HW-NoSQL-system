package kr.ac.dankook.NoSQLProject.controller;

import kr.ac.dankook.NoSQLProject.dto.request.PlaceRequest;
import kr.ac.dankook.NoSQLProject.dto.response.CoordinateResponse;
import kr.ac.dankook.NoSQLProject.dto.response.PlaceDistResponse;
import kr.ac.dankook.NoSQLProject.dto.response.PlaceResponse;
import kr.ac.dankook.NoSQLProject.exception.BadRequestNearbyPlaceException;
import kr.ac.dankook.NoSQLProject.exception.BadRequestSaveEntityException;
import kr.ac.dankook.NoSQLProject.exception.ErrorCode;
import kr.ac.dankook.NoSQLProject.service.LocationService;
import kr.ac.dankook.NoSQLProject.service.PlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class PlaceController {

    private final LocationService locationService;
    private final PlaceService placeService;

    @PostMapping("/place")
    public ResponseEntity<PlaceResponse> savePlace(@RequestBody PlaceRequest placeRequest) {
        if (placeRequest.getName() == null || placeRequest.getAddress() == null) {
            throw new BadRequestSaveEntityException(ErrorCode.BAD_REQUEST_OF_SAVE_ENTITY);
        }
        CoordinateResponse coordinate = locationService.getCoordinateProcess(
                placeRequest.getAddress()
        );
        return ResponseEntity.ok(placeService.savePlace(placeRequest, coordinate));
    }

    @GetMapping("/places")
    public ResponseEntity<List<PlaceResponse>> getPlaces() {
        return ResponseEntity.ok(placeService.getAllPlace());
    }

    @GetMapping("/place/nearby")
    public ResponseEntity<List<PlaceDistResponse>> nearbyPlace(@RequestBody Map<String, String> addressBody) {
        String address = addressBody.get("address");
        if (address == null) {
            throw new BadRequestNearbyPlaceException(ErrorCode.BAD_REQUEST_NEARBY_PLACE);
        }
        CoordinateResponse coordinate = locationService.getCoordinateProcess(address);
        return ResponseEntity.ok(placeService.nearbyPlace(coordinate));
    }

    @DeleteMapping("/place/{name}")
    public ResponseEntity<String> deletePlace(@PathVariable("name") String name) {
        return ResponseEntity.ok(placeService.deletePlace(name));
    }

    @DeleteMapping("/places")
    public ResponseEntity<String> deleteAllPlace(){
        placeService.deleteAllPlace();
        return ResponseEntity.ok("성공적으로 데이터를 모두 삭제하였습니다.");
    }

    @PostMapping("/place/dummy")
    public ResponseEntity<String> dummyInsert(){
        placeService.insertDummyData();
        return ResponseEntity.ok("성공적으로 테스트 데이터 5개를 삽입하였습니다.");
    }
}
