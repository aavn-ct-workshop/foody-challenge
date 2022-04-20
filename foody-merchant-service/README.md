# foody-merchant-service
Implementation of merchant service

# Specification
1. Create new <code>Merchant</code>

   POST ```/merchants```
   
   Request body:
   ```
    {
        "name": "Trung Nguyen Legends",
        "img": "https://cdn.trungnguyenlegend.com/wp-content/uploads/2018/03/new-logo.png",
        "foods": [
            {
                "name": "Bánh tráng trộn",
                "price": 5,
                "img": "https://i.ytimg.com/vi/8lNLepEuR8I/maxresdefault.jpg",
                "desc": "AirPods are Apple's completely wire-free headphones, which look a bit like the Apple EarPods from older devices, but without the cables."
            },
            {
                "name": "Cháo hàu sữa",
                "price": 7,
                "img": "https://image-us.eva.vn/upload/1-2020/images/2020-03-16/1584366195-716-thumbnail_schema_article.jpg",
                "desc": "Air Jordan (sometimes abbreviated AJ) is an American brand of basketball shoes, athletic, casual, and style clothing produced by Nike. Founded in Chicago, Air Jordan was created for Hall of Fame basketball player and six-time NBA Finals MVP Michael Jordan during his time with the Chicago Bulls."
            },
            {
                "name": "Chà xửa chân châu",
                "price": 3,
                "img": "https://dayphache.edu.vn/wp-content/uploads/2019/02/519cb84dfa56f4e64bd73c0393e49890.jpg",
                "desc": "A smartphone is a cell phone that allows you to do more than make phone calls and send text messages. Smartphones can browse the Internet and run software programs like a computer. ... There are thousands of smartphone apps including games, personal-use, and business-use programs that all run on the phone"
            }
        ]
    }
   ```
   Response (ID of created merchant):
   ```
   14aa9e4f-aef5-4e3d-96ee-3ad3e892e48d
   ```
2. Search <code>Merchant</code>
  
   GET ```/merchants?limit=10&offset=0```
   
   Request params:
   ```
   limit: 10 (Numbers of merchant return.)
   offset: 0 (Skip that many merchants before beginning to return.)
   ```
   
   If limit and offset not set, service will return all merchants.
   
   Response:
   ```
    [
      {
          "id": "25f39d0f-95b7-4d8b-9ea0-2259f0745fc8",
          "name": "Trung Nguyen Legends",
          "img": "https://cdn.trungnguyenlegend.com/wp-content/uploads/2018/03/new-logo.png",
          "foods": [
              {
                  "id": "1b349a3a-fd0e-4f5b-92c1-85d6449ffd7b",
                  "name": "Bánh tráng trộn",
                  "price": 5,
                  "img": "https://i.ytimg.com/vi/8lNLepEuR8I/maxresdefault.jpg",
                  "desc": "AirPods are Apple's completely wire-free headphones, which look a bit like the Apple EarPods from older devices, but without the cables."
              },
              {
                  "id": "8b2d4082-e37f-461c-bc70-4172697067ba",
                  "name": "Cháo hàu sữa",
                  "price": 7,
                  "img": "https://image-us.eva.vn/upload/1-2020/images/2020-03-16/1584366195-716-thumbnail_schema_article.jpg",
                  "desc": "Air Jordan (sometimes abbreviated AJ) is an American brand of basketball shoes, athletic, casual, and style clothing produced by Nike. Founded in Chicago, Air Jordan was created for Hall of Fame basketball player and six-time NBA Finals MVP Michael Jordan during his time with the Chicago Bulls."
              },
              {
                  "id": "fc16ec62-3b6f-46ed-9dca-2cf76665f830",
                  "name": "Chà xửa chân châu",
                  "price": 3,
                  "img": "https://dayphache.edu.vn/wp-content/uploads/2019/02/519cb84dfa56f4e64bd73c0393e49890.jpg",
                  "desc": "A smartphone is a cell phone that allows you to do more than make phone calls and send text messages. Smartphones can browse the Internet and run software programs like a computer. ... There are thousands of smartphone apps including games, personal-use, and business-use programs that all run on the phone"
              }
          ]
      }
    ]
   ```
   
   
