package org.example.services.interfaces;

import org.example.data.request.RegisterVolunteerRequest;

public interface IVolunteerService {
    int register(RegisterVolunteerRequest request);
}
