package org.example.service.entities.singersEntities;

import java.util.ArrayList;

public class GetTopSingersEntity {

    public class Attr {
        public Integer page;
        public Integer perPage;
        public Integer totalPages;
        public Integer total;
    }

    public class GetTopSingersBody {
        public ArrayList<GetTopItemSingersEntity> artist;
        public GetTopSingersEntity.Attr attr;
    }

    protected GetTopSingersEntity.GetTopSingersBody artists;

    public GetTopSingersEntity.GetTopSingersBody getSingers() {
        return artists;
    }

}
