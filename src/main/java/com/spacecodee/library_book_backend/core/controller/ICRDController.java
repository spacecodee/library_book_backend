package com.spacecodee.library_book_backend.core.controller;

import com.spacecodee.library_book_backend.core.controller.core.ICreateController;
import com.spacecodee.library_book_backend.core.controller.core.IDeleteController;
import com.spacecodee.library_book_backend.core.controller.core.IReadController;

public interface ICRDController<C, R, D> extends ICreateController<C>, IReadController<R>, IDeleteController<D> {
}
