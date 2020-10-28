package com.placeManage.model;


import lombok.AllArgsConstructor;
import lombok.Data;


import javax.persistence.*;
import java.util.UUID;


@Data
@AllArgsConstructor
public final class OccupiedTable {


    private UUID occupiedTableId;

    private UUID visitorsId;

    private UUID saloonId;

    public OccupiedTable() {
        occupiedTableId = UUID.randomUUID();
    }
}