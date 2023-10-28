# [SQL to MongoDB Mapping Chart](https://www.mongodb.com/docs/manual/reference/sql-comparison/)

| SQL Terms/Concepts                                           | MongoDB Terms/Concepts      |
| :----------------------------------------------------------- | :-------------------------- |
| database                                                     | database                    |
| table                                                        | collection                  |
| row                                                          | document<br />BSON document |
| column                                                       | field                       |
| index                                                        | index                       |
| table joins                                                  | \$lookup                    |
| primary key：<br />any unique column<br />column combination as primary key | primary key：<br />\_id     |
| aggregation（group by）                                      | aggregation pipeline        |
| select into new\_table                                       | \$out                       |
| merge into table                                             | \$merge                     |
| union all                                                    | \$unionWith                 |
| transactions                                                 | transactions                |