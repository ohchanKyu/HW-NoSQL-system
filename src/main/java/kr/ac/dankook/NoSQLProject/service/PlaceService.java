package kr.ac.dankook.NoSQLProject.service;

import kr.ac.dankook.NoSQLProject.document.Place;
import kr.ac.dankook.NoSQLProject.dto.request.LocationRequest;
import kr.ac.dankook.NoSQLProject.dto.request.PlaceRequest;
import kr.ac.dankook.NoSQLProject.dto.response.CoordinateResponse;
import kr.ac.dankook.NoSQLProject.dto.response.PlaceDistResponse;
import kr.ac.dankook.NoSQLProject.dto.response.PlaceResponse;
import kr.ac.dankook.NoSQLProject.repository.PlaceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Slf4j
public class PlaceService {

    private final PlaceRepository placeRepository;

    public List<PlaceResponse> getAllPlace(){
        List<Place> allPlaces = placeRepository.findAll();
        List<PlaceResponse> placeResponses = new ArrayList<>();
        for(Place place : allPlaces){
            placeResponses.add(new PlaceResponse(place.getName(),place.getAddress(),
                    place.getLocation().getY(),place.getLocation().getX()));
        }
        return placeResponses;
    }
    public String deletePlace(String placeName){
        Optional<Place> place = placeRepository.findByName(placeName);
        if (place.isEmpty()) {
            return "해당 장소명의 데이터가 존재하지 않습니다.";
        }
        placeRepository.deleteById(place.get().getId());
        return "데이터를 성공적으로 삭제하였습니다.";
    }
    public void deleteAllPlace(){
        placeRepository.deleteAll();
    }

    public PlaceResponse savePlace(PlaceRequest placeRequest, CoordinateResponse coordinate) {

        GeoJsonPoint geoJsonPoint = new GeoJsonPoint(coordinate.getLongitude(), coordinate.getLatitude());
        Place place = Place.builder()
                        .name(placeRequest.getName())
                        .address(placeRequest.getAddress())
                        .location(geoJsonPoint)
                        .build();
        placeRepository.save(place);
        return new PlaceResponse(place.getName(),
                place.getAddress(), geoJsonPoint.getY(), geoJsonPoint.getX());
    }
    
    public List<PlaceDistResponse> nearbyPlace(CoordinateResponse coordinate){
        GeoJsonPoint geoJsonPoint = new GeoJsonPoint(coordinate.getLongitude(), coordinate.getLatitude());
        List<Place> nearPlace = placeRepository.findByLocationNear(geoJsonPoint);
        List<PlaceDistResponse> responses = new ArrayList<>();
        for(Place place : nearPlace){
            LocationRequest locationRequest = LocationRequest.builder()
                    .startLatitude(coordinate.getLatitude())
                    .startLongitude(coordinate.getLongitude())
                    .endLatitude(place.getLocation().getY())
                    .endLongitude(place.getLocation().getX())
                    .build();
            double distance = getDistanceProcess(locationRequest);
            String distanceToString;
            if (distance >= 1){
                distanceToString = String.format("%.1fkm", distance);
            }else {
                distanceToString = String.format("%.1fm", distance * 1000);
            }
            responses.add(new PlaceDistResponse(place.getName(), place.getAddress(),distanceToString));
        }
        return responses;
    }
    public void insertDummyData(){
        Place[] places = new Place[5];
        places[0] = Place.builder()
                .name("죽전역").address("경기도 용인시 수지구 죽전동 1286 죽전역").location(
                        new GeoJsonPoint(127.10742517493931,37.325461845165435)).build();
        places[1] = Place.builder()
                .name("기흥역").address("경기도 용인시 기흥구 구갈동 657 기흥역 롯데캐슬 레이시티/주상복합").location(
                        new GeoJsonPoint(127.11505231923752,37.27454920301085)).build();
        places[2] = Place.builder()
                .name("홍대입구역").address("서울특별시 마포구 동교동 165 홍대입구역").location(
                        new GeoJsonPoint(126.92415848619726,37.55675374373833)).build();
        places[3] = Place.builder()
                .name("판교역").address("경기도 성남시 분당구 판교역로 지하160 (백현동)").location(
                        new GeoJsonPoint(127.111647692,37.383761158)).build();
        places[4] = Place.builder()
                .name("수원역").address("경기도 수원시 권선구 서둔동 425 수원역 웰리지 더파크").location(
                        new GeoJsonPoint(126.99088014014181,37.26167994284167)).build();
        placeRepository.saveAll(Arrays.asList(places));
    }

    private double getDistanceProcess(LocationRequest locationRequest) {
        double theta = locationRequest.getEndLongitude() - locationRequest.getStartLongitude();
        double dist = Math.sin(deg2rad(locationRequest.getStartLatitude())) *
                Math.sin(deg2rad(locationRequest.getEndLatitude())) +
                Math.cos(deg2rad(locationRequest.getStartLatitude())) *
                        Math.cos(deg2rad(locationRequest.getEndLatitude())) *
                        Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515 * 1609.344;
        return dist / 1000;
    }
    private double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }
    private double rad2deg(double rad) {
        return (rad * 180 / Math.PI);
    }
}
