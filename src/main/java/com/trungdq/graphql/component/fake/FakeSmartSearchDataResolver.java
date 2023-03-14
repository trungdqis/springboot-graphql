package com.trungdq.graphql.component.fake;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.InputArgument;
import com.trungdq.graphql.datasource.fake.FakeBookDataSource;
import com.trungdq.graphql.datasource.fake.FakeHelloDataSource;
import com.trungdq.graphql.generated.DgsConstants;
import com.trungdq.graphql.generated.types.SmartSearchResult;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@DgsComponent
public class FakeSmartSearchDataResolver {

    @DgsData(parentType = DgsConstants.QUERY_TYPE, field = DgsConstants.QUERY.SmartSearch)
    public List<SmartSearchResult> getSmartSearch(@InputArgument(name = "keyword") Optional<String> keyword) {
        var smartSearchList = new ArrayList<SmartSearchResult>();

        if (keyword.isEmpty()) {
            smartSearchList.addAll(FakeHelloDataSource.HELLO_LIST);
            smartSearchList.addAll(FakeBookDataSource.BOOK_LIST);
        } else {
            var keywordString = keyword.get();

            FakeHelloDataSource.HELLO_LIST.stream()
                    .filter(h -> StringUtils.containsIgnoreCase(h.getText(), keywordString))
                    .forEach(smartSearchList::add);

            FakeBookDataSource.BOOK_LIST.stream()
                    .filter(b -> StringUtils.containsIgnoreCase(b.getTitle(), keywordString))
                    .forEach(smartSearchList::add);
        }

        return smartSearchList;
    }
}
