package org.example.services.interfaces;

import org.example.data.request.AdoptHistoryRequest;
import org.example.data.request.ChangeStatusAdoptRequest;
import org.example.data.request.CreateAdoptionRequest;
import org.example.data.response.AdoptHistoryResponse;
import org.example.data.response.PaginationVO;
import org.example.data.response.ViewAdoptResponse;

public interface IAdoptService {
    PaginationVO<AdoptHistoryResponse> history(AdoptHistoryRequest request);
    ViewAdoptResponse view(int adoptId);
    int create(CreateAdoptionRequest request);
    int changeStatus(ChangeStatusAdoptRequest request);
    int approve(ChangeStatusAdoptRequest request);
}
