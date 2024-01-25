package com.example.helloworld;

import com.example.helloworld.request.OrganizationModel;
import com.example.helloworld.request.RestaurantModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/organizations")
public class OrganizationController {

    @Autowired
    private OrganizationRepository organizationRepository;
    private ModelMapper modelMapper = new ModelMapper();


    @GetMapping
    public List<Organization> getOrganization() {
        List<Organization> organizations = organizationRepository.findAll();
        return organizations;
    }

    @PostMapping()
    public OrganizationModel registerRestaurant(@RequestBody OrganizationModel organizationModel) {
        Organization organizationRequest = modelMapper.map(organizationModel, Organization.class);
        Organization organization = organizationRepository.save(organizationRequest);
        return modelMapper.map(organization, OrganizationModel.class);
    }

    @PatchMapping(value = "/{id}")
    public OrganizationModel patchOrganization(@PathVariable(value = "id") Long id, @RequestBody String jsonPatch) throws ApplicationException {
        Organization organization  = returnOrganizationIfExists(id);
        organization = ApiUtils.patch(organization, jsonPatch, Organization.class);
        Organization updatedOrganization = organizationRepository.save(organization);
        return modelMapper.map(updatedOrganization, OrganizationModel.class);
    }

    private Organization returnOrganizationIfExists(Long id) {
        Organization organization = organizationRepository.findById(id).orElse(null);
        if (ObjectUtils.isEmpty(organization)) {
            throw new NotFoundException("No organization exists with given search criteria.", "ORGANIZATION_NOT_FOUND_EXCEPTION", null);
        }
        return organization;
    }

}
