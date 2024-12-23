package kr.ac.dankook.NoSQLProject.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class PlaceRequest {

    private String name;
    private String address;
}
