package com.spacecodee.library_book_backend.core.controller;

import com.spacecodee.library_book_backend.core.controller.core.IDeleteController;
import com.spacecodee.library_book_backend.core.controller.core.IReadCrud;

public interface IRDController<E, D> extends IReadCrud<E>, IDeleteController<D> {
}
