package com.spacecodee.library_book_backend.core.service;

import com.spacecodee.library_book_backend.core.service.core.ICreateService;
import com.spacecodee.library_book_backend.core.service.core.IReadService;
import com.spacecodee.library_book_backend.core.service.core.IUpdateService;

public interface ICRUService<C, R, U> extends ICreateService<C>, IUpdateService<U>, IReadService<R> {
}
