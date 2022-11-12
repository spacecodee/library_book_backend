package com.spacecodee.library_book_backend.core.controller;

import com.spacecodee.library_book_backend.core.controller.core.ICreateController;
import com.spacecodee.library_book_backend.core.controller.core.IReadController;

public interface ICRController<C, R> extends ICreateController<C>, IReadController<R> {
}
