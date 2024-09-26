package com.nbe2.infra.emergencyroom.client;

import static com.nbe2.infra.openapi.properties.OpenApiProperties.*;

import java.util.List;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

import com.nbe2.domain.emergencyroom.RealTimeEmergencyInfo;
import com.nbe2.domain.emergencyroom.RealTimeEmergencyRoomReader;
import com.nbe2.domain.emergencyroom.Region;
import com.nbe2.infra.openapi.client.OpenApiFeignClient;
import com.nbe2.infra.openapi.dto.RealTimeEmergencyDataResponse;

@Component
@RequiredArgsConstructor
public class EmergencyRoomApiClient implements RealTimeEmergencyRoomReader {

    private final OpenApiFeignClient openApiFeignClient;

    @Override
    public List<RealTimeEmergencyInfo> getRealTimeEmergencyData(Region region) {
        return openApiFeignClient
                .getRealTimeEmergencyData(region.region(), region.subRegion(), NUM_OF_ROWS)
                .getItems()
                .stream()
                .map(RealTimeEmergencyDataResponse::toRealTimeEmergencyInfo)
                .toList();
    }
}
