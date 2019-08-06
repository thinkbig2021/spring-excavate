package com.yumeng.spring.graphql;

import graphql.GraphQL;
import graphql.Scalars;
import graphql.schema.GraphQLFieldDefinition;
import graphql.schema.GraphQLList;
import graphql.schema.GraphQLObjectType;
import graphql.schema.GraphQLSchema;

import java.util.ArrayList;
import java.util.List;

public class GraphqlTest {

    private void initOutputType() {
        userType = GraphQLObjectType.newObject().name("User")
                .field(GraphQLFieldDefinition.newFieldDefinition().name("id").type(Scalars.GraphQLInt).build())
                .field(GraphQLFieldDefinition.newFieldDefinition().name("age").type(Scalars.GraphQLInt).build())
                .field(GraphQLFieldDefinition.newFieldDefinition().name("sex").type(Scalars.GraphQLInt).build())
                .field(GraphQLFieldDefinition.newFieldDefinition().name("name").type(Scalars.GraphQLString).build())
                .field(GraphQLFieldDefinition.newFieldDefinition().name("pic").type(Scalars.GraphQLString).build())
                .build();
    }

    private GraphQLFieldDefinition createUserField() {
        return GraphQLFieldDefinition.newFieldDefinition().name("user")
                .argument(newArgument().name("id").type(Scalars.GraphQLInt).build()).type(userType)
                .dataFetcher(environment -> {
                    // 获取查询参数
                    int id = environment.getArgument("id");

                    // 执行查询, 这里随便用一些测试数据来说明问题
                    User user = new User();
                    user.setId(id);
                    user.setAge(id + 15);
                    user.setSex(id % 2);
                    user.setName("Name_" + id);
                    user.setPic("pic_" + id + ".jpg");
                    return user;
                }).build();
    }

    private GraphQLFieldDefinition createUsersField() {
        return GraphQLFieldDefinition.newFieldDefinition().name("users")
                .argument(newArgument().name("page").type(Scalars.GraphQLInt).build())
                .argument(newArgument().name("size").type(Scalars.GraphQLInt).build())
                .argument(newArgument().name("name").type(Scalars.GraphQLString).build())
                .type(new GraphQLList(userType)).dataFetcher(environment -> {
                    // 获取查询参数
                    int page = environment.getArgument("page");
                    int size = environment.getArgument("size");
                    String name = environment.getArgument("name");

                    // 执行查询, 这里随便用一些测试数据来说明问题
                    List<User> list = new ArrayList<>(size);
                    for (int i = 0; i < size; i++) {
                        User user = new User();
                        user.setId(i);
                        user.setAge(i + 15);
                        user.setSex(i % 2);
                        user.setName(name + "_" + page + "_" + i);
                        user.setPic("pic_" + i + ".jpg");
                        list.add(user);
                    }
                    return list;
                }).build();
    }


    public static void main(String[] args) {
        String query1 = "{users(page:2,size:5,name:\"john\") {id,sex,name,pic}}";
        String query2 = "{user(id:6) {id,sex,name,pic}}";
        String query3 = "{user(id:6) {id,sex,name,pic},users(page:2,size:5,name:\"john\") {id,sex,name,pic}}";

        GraphQLSchema schema = new HelloGraphql().getSchema();

        Object result1 = GraphQL.newGraphQL(schema).build().execute(query1).getData();
        Object result2 = GraphQL.newGraphQL(schema).build().execute(query2).getData();
        Object result3 = GraphQL.newGraphQL(schema).build().execute(query3).getData();

        // 查询用户列表
        System.out.println(result1);
        // 查询单个用户
        System.out.println(result2);
        // 单个用户、跟用户列表一起查
        System.out.println(result3);
    }


}
