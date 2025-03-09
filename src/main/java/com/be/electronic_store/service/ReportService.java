package com.be.electronic_store.service;

import com.be.electronic_store.model.ReceiptProductDTO;

public interface ReportService {

    ReceiptProductDTO getReceiptProducts(long userId);
}