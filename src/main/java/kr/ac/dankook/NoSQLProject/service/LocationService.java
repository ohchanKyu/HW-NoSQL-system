package kr.ac.dankook.NoSQLProject.service;

import kr.ac.dankook.NoSQLProject.dto.response.CoordinateResponse;
import kr.ac.dankook.NoSQLProject.exception.ApiJsonParsingException;
import kr.ac.dankook.NoSQLProject.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@RequiredArgsConstructor
@Slf4j
public class LocationService {

    @Value("${api.kakao.app_key}")
    private String KAKAO_APP_KEY;
    @Value("${api.kakao.address-to-coordinate}")
    private String KAKAO_ADDRESS_TO_COORDINATE;
    private final RestTemplate restTemplate = new RestTemplate();

    public CoordinateResponse getCoordinateProcess(String address){

        String apiKey = "KakaoAK " + KAKAO_APP_KEY;

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization",apiKey);
        HttpEntity<String> entity = new HttpEntity<>(httpHeaders);
        UriComponents uriComponents = UriComponentsBuilder.fromUriString(KAKAO_ADDRESS_TO_COORDINATE)
                .queryParam("query",address)
                .build();
        ResponseEntity<String> response = restTemplate.exchange(uriComponents.toString(), HttpMethod.GET, entity,String.class);

        String body = response.getBody();
        try{
            JSONObject json = new JSONObject(body);
            JSONArray documents = json.getJSONArray("documents");
            double longitude = documents.getJSONObject(0).getDouble("x");
            double latitude = documents.getJSONObject(0).getDouble("y");
            return new CoordinateResponse(latitude,longitude);
        }catch(JSONException e){
            log.info("Coordinate Process Error");
            throw new ApiJsonParsingException(ErrorCode.API_JSON_PARSING_ERROR);
        }
    }
}
