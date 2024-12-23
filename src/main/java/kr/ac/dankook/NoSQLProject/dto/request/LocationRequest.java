package kr.ac.dankook.NoSQLProject.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class LocationRequest {
    private double startLatitude;
    private double startLongitude;
    private double endLatitude;
    private double endLongitude;
}
