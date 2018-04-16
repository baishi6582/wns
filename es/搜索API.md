## 搜索API
ES提供了两周搜索方式，一种是将搜索参数添加的REST请求的URI当中，另一种是将参数放在REST请求的结构体当中。使用请求体的方式允许将会更加容易表达以及允许我们使用更易阅读的JSON结构。不过，首先展示一个URI的访问方式。REST请求的开始点在_search部分，下边这个请求将会按照account_number升序的格式返回所有的数据集，不过我们通过前台只能看到10条。
<pre>
curl -X GET "http://localhost:9200/bank/_search?q=*&sort=account_number:asc&pretty"
</pre>
> 其中 pretty仅是告诉ES将结果展示成我们熟悉JSON格式，以便于阅读
<pre>
{
  "took" : 3,
  "timed_out" : false,
  "_shards" : {
    "total" : 5,
    "successful" : 5,
    "skipped" : 0,
    "failed" : 0
  },
  "hits" : {
    "total" : 1000,
    "max_score" : null,
    "hits" : [
      {
        "_index" : "bank",
        "_type" : "account",
        "_id" : "0",
        "_score" : null,
        "_source" : {
          "account_number" : 0,
          "balance" : 16623,
          "firstname" : "Bradshaw",
          "lastname" : "Mckenzie",
          "age" : 29,
          "gender" : "F",
          "address" : "244 Columbus Place",
          "employer" : "Euron",
          "email" : "bradshawmckenzie@euron.com",
          "city" : "Hobucken",
          "state" : "CO"
        },
        "sort" : [
          0
        ]
      },
      {
        "_index" : "bank",
        "_type" : "account",
        "_id" : "1",
        "_score" : null,
        "_source" : {
          "account_number" : 1,
          "balance" : 39225,
          "firstname" : "Amber",
          "lastname" : "Duke",
          "age" : 32,
          "gender" : "M",
          "address" : "880 Holmes Lane",
          "employer" : "Pyrami",
          "email" : "amberduke@pyrami.com",
          "city" : "Brogan",
          "state" : "IL"
        },
        "sort" : [
          1
        ]
      },...
	  ]
	}
}
</pre>
* took -ES在搜索过程中耗时
* time_out -搜索操作是否超时
* _shards -在多少shard中完成了搜索，同时会提供成功的shard数以及失败的shard数
* hits -搜索结果
* hits.total -满足条件的总数
* hits.hits -实际搜索结果（默认展示10条）
* hits.sort -结果排序的key（没有的话，按照score排序）
* hits._socre 和 max_score -当前忽略这些字段

相同的搜索使用请求体的搜索方式。
<pre>
curl -X GET "http://localhost:9200/bank/_search?pretty" -H 'Content-Type: application/json' -d'
{
  "query": { "match_all": {} },
  "sort": [
    { "account_number": "asc" }
  ]
}
'
</pre>
需要注意的是ES一旦返回搜索结果，则表示完全完成了这个请求，不会去维护任何结果的游标。这点是与SQl数据库不同的。
