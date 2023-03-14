package com.trungdq.graphql.component.fake;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.InputArgument;
import com.trungdq.graphql.datasource.fake.FakeBookDataSource;
import com.trungdq.graphql.generated.types.Book;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@DgsComponent
public class FakeBookDataResolver {

    // if you want to use different method name
    @DgsData(parentType = "Query", field = "books")
    public List<Book> booksWrittenBy(@InputArgument(name = "author") Optional<String> authorName) {
        if (authorName.isEmpty() || StringUtils.isBlank(authorName.get())) {
            return FakeBookDataSource.BOOK_LIST;
        }
        return FakeBookDataSource.BOOK_LIST.stream()
                .filter(b -> StringUtils.containsIgnoreCase(
                        b.getAuthor().getName(), authorName.get()
                )).collect(Collectors.toList());
    }
}
