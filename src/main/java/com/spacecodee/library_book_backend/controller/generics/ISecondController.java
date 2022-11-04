package com.spacecodee.library_book_backend.controller.generics;

import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("default")
public interface ISecondController<L, A> extends IAllController<L, A, A> {
}
