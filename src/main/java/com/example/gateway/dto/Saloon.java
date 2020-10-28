package com.example.gateway.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public final class Saloon {
    private UUID tableId;

    private int placeNum;

    private String uniqueName;

    private UUID occupiedTableId;

    private UUID freeTableId;

    public Saloon() {
        tableId = UUID.randomUUID();
    }

    public Saloon(String uniqueName, int placeNum) {
        tableId = UUID.randomUUID();
        this.placeNum=placeNum;
        this.uniqueName=uniqueName;
    }
}
