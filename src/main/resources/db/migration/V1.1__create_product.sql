CREATE TABLE product
(
    uuid        UUID             NOT NULL DEFAULT uuid_generate_v1(),
    CONSTRAINT product_pkey PRIMARY KEY (uuid),
    name        VARCHAR(100)     NOT NULL,
    price       DOUBLE PRECISION NOT NULL,
    description VARCHAR(2000)
);

INSERT INTO product (name, price, description)
VALUES ('Computer', '2000',
        'This model features the high-speed, high-performance Intel Core I5 Processor 3.20 ghz, allowing you to open multiple tabs in your browser, watch movies, play games and use other programs without the loss of speed and performance.\n\n' ||
        'Storage on your 2TB hard drive gives you plenty of storage space for documents, photos, videos, files and movies for you to enjoy freely without having to remove important files for lack of space.\n' ||
        'Ram Memory: 8gb Ddr3 1333mhz, making your computer faster powerful in its daily activities.\n' ||
        'Intel processor + chipset that guarantees incredible performance and speed.\n' ||
        'Dvdrw: to meet all your DVD read and write needs like Movies, Music, etc...');
INSERT INTO product (name, price, description)
VALUES ('Printer', '800', 'HP All-in-One Printer - DeskJet Ink Advantage 3776 Wi-Fi Inkjet\n\n' ||
                          'Save space, money and print wirelessly with the world''s smallest multifunction printer. Get low-cost color and get all the power you need with amazing, compact styling. Print, scan and copy from virtually anywhere with the powerful portable all-in-one printer.' ||
                          'The small, powerful all-in-one printer saves space and has all the power you need. HP Scroll Scan helps you easily handle most scanning jobs on a variety of paper in any room or anywhere, this extremely compact all-in-one printer is designed to fit and fit' ||
                          'where you need it. Show off your style with a sleek design, print quickly from your mobile device, the easiest way to print documents, photos, and more from your Apple, Android, and Windows devices. Connect your smartphone or tablet directly to your printer,' ||
                          'and print easily. Scan any object on the go with the HP All-in-One Printer Remote mobile app. Fits your budget and fits your needs Count on quality printing with genuine HP ink cartridges. Easily recycle genuine HP supplies free of charge through the HP Planet Partners program.');
INSERT INTO product (name, price)
VALUES ('Mouse', '80');
INSERT INTO product (name, price)
VALUES ('Office desk', '300')