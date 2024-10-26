package org.example.services.interfaces;

import org.example.data.request.ConfirmApplicationRequest;
import org.example.data.request.RegisterVolunteerRequest;
import org.example.data.response.VolunteerDetailResponse;

public interface IVolunteerService {
    int register(RegisterVolunteerRequest request);
    VolunteerDetailResponse detail(int userId);
    int changeStatus(ConfirmApplicationRequest request);
}
