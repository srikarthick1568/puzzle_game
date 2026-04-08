CREATE TABLE `bundle_product_header` (
  `cmpcode` varchar(20) NOT NULL,
  `productcode` varchar(50) NOT NULL,
  `productname` varchar(255) DEFAULT NULL,
  `shortname` varchar(100) DEFAULT NULL,
  `parentcode` varchar(50) DEFAULT NULL,
  `franchise` varchar(100) DEFAULT NULL,
  `brand` varchar(100) DEFAULT NULL,
  `product_category` varchar(100) DEFAULT NULL,
  `variant` varchar(100) DEFAULT NULL,
  `mothersku` varchar(100) DEFAULT NULL,
  `producttype` varchar(50) DEFAULT NULL,
  `vmrtype` varchar(50) DEFAULT NULL,
  `eancode` varchar(100) DEFAULT NULL,
  `netwgt` double DEFAULT NULL,
  `weighttype` varchar(50) DEFAULT NULL,
  `shelflife` int DEFAULT NULL,
  `ptrperpc` double DEFAULT NULL,
  `volume` varchar(50) DEFAULT NULL,
  `standardnr` varchar(50) DEFAULT NULL,
  `caselot` int DEFAULT NULL,
  `isexempted` tinyint(1) DEFAULT NULL,
  `status` varchar(50) DEFAULT NULL,
  `international_uom` varchar(50) DEFAULT NULL,
  `moddt` datetime DEFAULT NULL,
  PRIMARY KEY (`cmpcode`,`productcode`)
) ;


CREATE TABLE bundleproductdetails (

    parentcode VARCHAR(50) NOT NULL,
    childcode VARCHAR(50) NOT NULL,

    qty INT,

    -- Snapshot fields (copied from header)

    productname VARCHAR(255),
    shortname VARCHAR(100),
    franchise VARCHAR(100),
    brand VARCHAR(100),
    productcategory VARCHAR(100),
    variant VARCHAR(100),
    mothersku VARCHAR(100),
    producttype VARCHAR(50),
    vmrtype VARCHAR(50),

    eancode VARCHAR(100),
    netwgt DOUBLE,
    weighttype VARCHAR(50),
    shelflife INT,
    ptrperpc DOUBLE,
    volume VARCHAR(50),
    standardnr VARCHAR(50),
    caselot INT,

    isexempted BOOLEAN,
    status VARCHAR(50),
    internationaluom VARCHAR(50),

    PRIMARY KEY (parentcode, childcode),

    CONSTRAINT fk_bundleparent
    FOREIGN KEY (parentcode)
    REFERENCES bundleproductheader(productcode)
    ON DELETE CASCADE

);
