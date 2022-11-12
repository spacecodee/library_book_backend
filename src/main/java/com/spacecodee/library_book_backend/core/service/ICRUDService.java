package com.spacecodee.library_book_backend.core.service;

import com.spacecodee.library_book_backend.core.service.core.ICreateService;
import com.spacecodee.library_book_backend.core.service.core.IDeleteService;
import com.spacecodee.library_book_backend.core.service.core.IReadService;
import com.spacecodee.library_book_backend.core.service.core.IUpdateService;

public interface ICRUDService<C, R, U, D> extends ICreateService<C>, IUpdateService<U>, IReadService<R>,
                                                  IDeleteService<D> {
}
