package org.example.services.interfaces;

import org.example.data.request.CreateAdoptionRequest;
import org.example.data.request.CreatePetRequest;
import org.example.data.request.FilterPetRequest;
import org.example.data.request.UpdateAdoptionRequest;
import org.example.data.response.AdoptHistoryResponse;
import org.example.data.response.FilterPetResponse;
import org.example.data.response.PaginationVO;


public interface IPetService {
    PaginationVO<FilterPetResponse> filter(FilterPetRequest request);
    int create(CreatePetRequest request);
    int update(UpdateAdoptionRequest request);
    int delete(int adoptId);
}
