package com.spacecodee.library_book_backend.core.controller;

import com.spacecodee.library_book_backend.core.controller.core.ICreateController;
import com.spacecodee.library_book_backend.core.controller.core.IDeleteController;
import com.spacecodee.library_book_backend.core.controller.core.IReadCrud;

public interface ICRDController<E, D> extends IReadCrud<E>, ICreateController<E>, IDeleteController<D> {
}
