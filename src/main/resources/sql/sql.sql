-- 修改公告表 2个字段的长度为max
ALTER TABLE AdminManager.dbo.bulletin ALTER COLUMN content VARCHAR(MAX);
ALTER TABLE AdminManager.dbo.bulletin ALTER COLUMN [desc] VARCHAR(MAX);

-- 删除 salary_balance 字段
ALTER TABLE AdminManager.dbo.agency DROP COLUMN salary_balance;

CREATE TABLE AdminManager.dbo.coin_record
(
    id INT PRIMARY KEY NOT NULL IDENTITY,
    record_type INT,
    from_agency_id INT,
    to_agency_id INT,
    to_user_id INT,
    quantity INT,
    trans_datetime DATETIME
);
CREATE TABLE AdminManager.dbo.room_card_record
(
    id INT PRIMARY KEY NOT NULL IDENTITY,
    record_type INT,
    from_agency_id INT,
    to_agency_id INT,
    to_user_id INT,
    quantity INT,
    trans_datetime DATETIME
);
ALTER TABLE AdminManager.dbo.agency DROP COLUMN avatar;
ALTER TABLE AdminManager.dbo.agency ADD avatar VARCHAR(MAX) NULL;

ALTER TABLE AdminManager.dbo.bulletin DROP COLUMN image;
ALTER TABLE AdminManager.dbo.bulletin ADD image VARCHAR(MAX) NULL;

CREATE TABLE AdminManager.dbo.month_view
(
    month INT
);
INSERT INTO month_view VALUES (1),(2),(3),(4),(5),(6),(7),(8),(9),(10),(11),(12);

CREATE TABLE AdminManager.dbo.performance
(
    id                  INT PRIMARY KEY IDENTITY,
    agency_id            INT,
    level               INT,
    money               INT,
    cash_back_datetime  DATETIME,
    record_buy_order_id INT
);

ALTER TABLE AdminManager.dbo.agency ADD qrcode VARCHAR(MAX) NULL;