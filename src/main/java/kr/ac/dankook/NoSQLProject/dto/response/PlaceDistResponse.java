package kr.ac.dankook.NoSQLProject.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class PlaceDistResponse {

    private String name;
    private String address;
    private String distance;
}
