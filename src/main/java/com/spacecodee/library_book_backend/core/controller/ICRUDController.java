package com.spacecodee.library_book_backend.core.controller;

import com.spacecodee.library_book_backend.core.controller.core.ICreateController;
import com.spacecodee.library_book_backend.core.controller.core.IDeleteController;
import com.spacecodee.library_book_backend.core.controller.core.IReadCrud;
import com.spacecodee.library_book_backend.core.controller.core.IUpdateController;

public interface ICRUDController<C, R, U, D> extends ICreateController<C>, IUpdateController<U>, IReadCrud<R>,
                                                     IDeleteController<D> {
}
