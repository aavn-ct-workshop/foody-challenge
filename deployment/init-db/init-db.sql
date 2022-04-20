CREATE DATABASE "workshop-order-service";
CREATE DATABASE "workshop-merchant-service";
CREATE DATABASE "payment";
GRANT ALL PRIVILEGES ON DATABASE "workshop-order-service" TO "postgres";
GRANT ALL PRIVILEGES ON DATABASE "payment" TO "postgres";
GRANT ALL PRIVILEGES ON DATABASE "workshop-merchant-service" TO "postgres";