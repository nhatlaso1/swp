package org.example.services.interfaces;

import org.example.data.request.FilterPetRequest;
import org.example.data.response.FilterPetResponse;
import org.example.data.response.PaginationVO;


public interface IPetService {
    PaginationVO<FilterPetResponse> filter(FilterPetRequest request);
}
