package com.trungdq.graphql.component.fake;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsQuery;
import com.trungdq.graphql.datasource.fake.FakeHelloDataSource;
import com.trungdq.graphql.generated.types.Hello;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@DgsComponent
public class FakeHelloDataResolver {

    // name must be exact
    @DgsQuery
    public List<Hello> allHellos() {
        return FakeHelloDataSource.HELLO_LIST;

    }

    @DgsQuery
    public Hello oneHello() {
        return FakeHelloDataSource.HELLO_LIST.get(
                ThreadLocalRandom.current().nextInt(FakeHelloDataSource.HELLO_LIST.size())
        );
    }
}
