package kr.ac.dankook.NoSQLProject.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PlaceResponse {

    private String name;
    private String address;
    private double latitude;
    private double longitude;
}
