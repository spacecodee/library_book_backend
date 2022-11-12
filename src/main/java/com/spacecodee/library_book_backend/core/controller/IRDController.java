package com.spacecodee.library_book_backend.core.controller;

import com.spacecodee.library_book_backend.core.controller.core.IDeleteController;
import com.spacecodee.library_book_backend.core.controller.core.IReadController;

public interface IRDController<E, D> extends IReadController<E>, IDeleteController<D> {
}
