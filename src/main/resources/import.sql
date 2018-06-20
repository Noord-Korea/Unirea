INSERT into Building (BUILDING_ID, name) VALUES (1, 'Oil Refinery') ON DUPLICATE KEY UPDATE name = 'Oil Refinery';
INSERT into Building (BUILDING_ID, name) VALUES (2, 'Iron Mine') ON DUPLICATE KEY UPDATE name = 'Iron Mine';
INSERT into Building (BUILDING_ID, name) VALUES (3, 'Forestry') ON DUPLICATE KEY UPDATE name = 'Forestry';
INSERT into Building (BUILDING_ID, name) VALUES (4, 'Headquarters') ON DUPLICATE KEY UPDATE name = 'Headquarters';
INSERT into Building (BUILDING_ID, name) VALUES (5, 'Warehouse') ON DUPLICATE KEY UPDATE name = 'Warehouse';
INSERT into Building (BUILDING_ID, name) VALUES (6, 'Barracks') ON DUPLICATE KEY UPDATE name = 'Barracks';
INSERT into Building (BUILDING_ID, name) VALUES (7, 'Training grounds') ON DUPLICATE KEY UPDATE name = 'Training grounds';
INSERT into Building (BUILDING_ID, name) VALUES (8, 'Foundry') ON DUPLICATE KEY UPDATE name = 'Foundry';
INSERT into Building (BUILDING_ID, name) VALUES (9, 'Ammunition Depot') ON DUPLICATE KEY UPDATE name = 'Ammunition Depot';
INSERT into Building (BUILDING_ID, name) VALUES (10, 'Wall') ON DUPLICATE KEY UPDATE name =  'Wall';

INSERT into Resource (RESOURCE_ID, name) VALUES (1, 'Oil') ON DUPLICATE KEY UPDATE name = 'Oil';
INSERT into Resource (RESOURCE_ID, name) VALUES (2, 'Iron') ON DUPLICATE KEY UPDATE name = 'Iron';
INSERT into Resource (RESOURCE_ID, name) VALUES (3, 'Wood') ON DUPLICATE KEY UPDATE name = 'Wood';