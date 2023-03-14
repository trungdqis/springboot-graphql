package com.trungdq.graphql.component.fake;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.InputArgument;
import com.trungdq.graphql.datasource.fake.FakePetDataSource;
import com.trungdq.graphql.generated.DgsConstants;
import com.trungdq.graphql.generated.types.Cat;
import com.trungdq.graphql.generated.types.Dog;
import com.trungdq.graphql.generated.types.Pet;
import com.trungdq.graphql.generated.types.PetFilter;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@DgsComponent
public class FakePetDataResolver {

    @DgsData(parentType = DgsConstants.QUERY_TYPE, field = DgsConstants.QUERY.Pets)
    public List<Pet> getPets(@InputArgument(name = "petFilter", collectionType = PetFilter.class)
                             Optional<PetFilter> filter) {
        if (filter.isEmpty()) {
            return FakePetDataSource.PET_LIST;
        }

        return FakePetDataSource.PET_LIST.stream()
                .filter(pet -> this.matchFilter(filter.get(), pet))
                .collect(Collectors.toList());
    }

    private boolean matchFilter(PetFilter petFilter, Pet pet) {
        if (StringUtils.isBlank(petFilter.getPetType())) {
            return true;
        }

        if (petFilter.getPetType().equalsIgnoreCase(Dog.class.getSimpleName())) {
            return pet instanceof Dog;
        } else if (petFilter.getPetType().equalsIgnoreCase(Cat.class.getSimpleName())) {
            return pet instanceof Cat;
        } else {
            return false;
        }
    }
}
