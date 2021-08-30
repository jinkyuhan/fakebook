package com.jkhan.fakebookserver.common;

import lombok.Getter;

import java.util.Date;
import java.util.UUID;

@Getter
public class PageCursorVo {
    private final UUID idCursor;
    private final Date dateCursor;

    private PageCursorVo(UUID id, Long timestamp) {
        this.idCursor = id;
        this.dateCursor = new Date(timestamp);
    }

    public static PageCursorVo of(UUID id, Long timestamp) {
        return new PageCursorVo(id, timestamp);
    };
}
