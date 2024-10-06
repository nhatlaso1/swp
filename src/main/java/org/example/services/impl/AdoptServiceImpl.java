package org.example.services.impl;

import org.example.data.request.AdoptHistoryRequest;
import org.example.data.response.AdoptHistoryResponse;
import org.example.data.response.PaginationVO;
import org.example.repositories.AdoptionRepository;
import org.example.services.interfaces.IAdoptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdoptServiceImpl implements IAdoptService {
    @Autowired
    private AdoptionRepository adoptionRepository;
    @Override
    public PaginationVO<AdoptHistoryResponse> history(AdoptHistoryRequest request) {
        return null;
    }
}
