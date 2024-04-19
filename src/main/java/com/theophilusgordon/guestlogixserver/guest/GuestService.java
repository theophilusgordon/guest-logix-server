package com.theophilusgordon.guestlogixserver.guest;

import com.theophilusgordon.guestlogixserver.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

@Service
@RequiredArgsConstructor
public class GuestService {
    private final GuestRepository guestRepository;

    public void registerGuest(GuestRegisterRequest request){
        Guest guest = Guest.builder()
            .firstName(request.getFirstName())
            .middleName(request.getMiddleName())
            .lastName(request.getLastName())
            .email(request.getEmail())
            .phone(request.getPhone())
            .profilePhotoUrl(request.getProfilePhotoUrl())
            .company(request.getCompany())
            .build();
        guestRepository.save(guest);
    }

    public void updateGuest(@PathVariable String id, GuestUpdateRequest request){
        Guest guest = guestRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("Guest", id));
        if(request.getFirstName() != null)
            guest.setFirstName(request.getFirstName());
        if(request.getMiddleName() != null)
            guest.setMiddleName(request.getMiddleName());
        if(request.getLastName() != null)
            guest.setLastName(request.getLastName());
        if(request.getPhone() != null)
            guest.setPhone(request.getPhone());
        if(request.getProfilePhotoUrl() != null)
            guest.setProfilePhotoUrl(request.getProfilePhotoUrl());
        if(request.getCompany() != null)
            guest.setCompany(request.getCompany());
        guestRepository.save(guest);
    }

    public Iterable<Guest> getAllGuests(){
        return guestRepository.findAll();
    }

    public Guest getGuest(@PathVariable String id){
        return guestRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("Guest", id));
    }

    public void deleteGuest(@PathVariable String id){
        guestRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("Guest", id));
        guestRepository.deleteById(id);
    }
}