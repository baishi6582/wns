创建索引，踩到的坑，如果需要对field进行聚合，则必须将其设置为keyword类型，否则会出现异常信息。

```
curl  -H "Content-Type: application/json" -X PUT http://192.168.0.101:9200/cars -d'
{
	"aliases": {},
	"mappings": {
		"transactions": {
			"properties": {
				"color": {
					"type": "keyword"
				},
				"make": {
					"type": "keyword"
				},
				"price": {
					"type": "long"
				},
				"sold": {
					"type": "date"
				}
			}
		}
	},
	"settings": {
		"index": {
			"number_of_shards": "5",
			"number_of_replicas": "1"
		}
	}
}'
```

bulk插入数据

```
curl -X POST "http://192.168.0.101:9200/cars/transactions/_bulk" -H 'Content-Type: application/json' -d'
{ "index": {}}
{ "price" : 10000, "color" : "red", "make" : "honda", "sold" : "2014-10-28" }
{ "index": {}}
{ "price" : 20000, "color" : "red", "make" : "honda", "sold" : "2014-11-05" }
{ "index": {}}
{ "price" : 30000, "color" : "green", "make" : "ford", "sold" : "2014-05-18" }
{ "index": {}}
{ "price" : 15000, "color" : "blue", "make" : "toyota", "sold" : "2014-07-02" }
{ "index": {}}
{ "price" : 12000, "color" : "green", "make" : "toyota", "sold" : "2014-08-19" }
{ "index": {}}
{ "price" : 20000, "color" : "red", "make" : "honda", "sold" : "2014-11-05" }
{ "index": {}}
{ "price" : 80000, "color" : "red", "make" : "bmw", "sold" : "2014-01-01" }
{ "index": {}}
{ "price" : 25000, "color" : "blue", "make" : "ford", "sold" : "2014-02-12" }
'
```

根据color进行聚合，其中size=0，不关心查询结果，提升查询效率，通过postman发生请求，由于GET不支持body的设置，因此，可以使用POST方式进行发送
```
curl -X GET "http://192.168.0.101:9200/cars/transactions/_search" -H 'Content-Type: application/json' -d'
{
    "size" : 0,
    "aggs" : { 
        "popular_colors" : { 
            "terms" : { 
              "field" : "color"
            }
        }
    }
}
'
```

结果：
```
{
    "took": 54,
    "timed_out": false,
    "_shards": {
        "total": 5,
        "successful": 5,
        "skipped": 0,
        "failed": 0
    },
    "hits": {
        "total": 8,
        "max_score": 0,
        "hits": []
    },
    "aggregations": {
        "popular_colors": {
            "doc_count_error_upper_bound": 0,
            "sum_other_doc_count": 0,
            "buckets": [
                {
                    "key": "red",
                    "doc_count": 4
                },
                {
                    "key": "blue",
                    "doc_count": 2
                },
                {
                    "key": "green",
                    "doc_count": 2
                }
            ]
        }
    }
}

```
