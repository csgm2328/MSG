package com.web.c101.newStore;

public class NewStoreAdaptor {
    public static NewStoreDto entityToDto(NewStore newStore) {
        return NewStoreDto.builder()
                .sid(newStore.getSid())
                .name(newStore.getName())
                .area(newStore.getName())
                .build();
    }

    public static NewStore dtoToEntity(NewStoreDto newStoreDto) {
        return NewStore.builder()
                .sid(newStoreDto.getSid())
                .name(newStoreDto.getName())
                .area(newStoreDto.getName())
                .build();
    }
}
