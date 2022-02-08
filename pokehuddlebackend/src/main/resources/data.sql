DELETE
FROM userroles;

DELETE
FROM roles;

DELETE
FROM articles;

--DELETE
--FROM preference;

DELETE
FROM users;



INSERT INTO users (userid, username, email, password, createdby, createddate, lastmodifiedby, lastmodifieddate)
    VALUES (1, 'neo', 'neo@pikipika.com', 'pika123', 'SYSTEM', CURRENT_TIMESTAMP, 'SYSTEM', CURRENT_TIMESTAMP),
           (2, 'Scarlett', 'scar@pikapika.com', 'pika123', 'SYSTEM', CURRENT_TIMESTAMP, 'SYSTEM', CURRENT_TIMESTAMP);

INSERT INTO roles (roleid, role, createdby, createddate, lastmodifiedby, lastmodifieddate)
    VALUES (1, 'admin', 'SYSTEM', CURRENT_TIMESTAMP, 'SYSTEM', CURRENT_TIMESTAMP),
           (2, 'member', 'SYSTEM', CURRENT_TIMESTAMP, 'SYSTEM', CURRENT_TIMESTAMP);

INSERT INTO articles (articleid, title, body, author, userid, createdby, createddate, lastmodifiedby, lastmodifieddate)
    VALUES (1, 'Pikachu is too popular', 'Sic de isto et tutius perducit ad actum ipsum, ut si dico Ego autem vadam lavari, ut mens mea in statu naturae conformior. Et similiter circa alias', 'Neo', 1, 'SYSTEM', CURRENT_TIMESTAMP, 'SYSTEM', CURRENT_TIMESTAMP),
           (2, 'Top Ash Mistakes', 'Sic de isto et tutius perducit ad actum ipsum, ut si dico Ego autem vadam', 'Neo', 1, 'SYSTEM', CURRENT_TIMESTAMP, 'SYSTEM', CURRENT_TIMESTAMP),
           (3, 'Mew vs Mewtwo', 'Sic de isto et tutius perducit ad actum ipsum, ut si dico “Ego autem vadam', 'Scarlett', 2, 'SYSTEM', CURRENT_TIMESTAMP, 'SYSTEM', CURRENT_TIMESTAMP);

INSERT INTO userroles (userid, roleid, createdby, createddate, lastmodifiedby, lastmodifieddate)
    VALUES (1, 1, 'SYSTEM', CURRENT_TIMESTAMP, 'SYSTEM', CURRENT_TIMESTAMP),
           (2, 1, 'SYSTEM', CURRENT_TIMESTAMP, 'SYSTEM', CURRENT_TIMESTAMP),
           (2, 2, 'SYSTEM', CURRENT_TIMESTAMP, 'SYSTEM', CURRENT_TIMESTAMP);

--INSERT INTO preference (preferenceid, avatar, colorscheme)
--    VALUES (4, 'pikachu', "maroon"),
--           (5, 'mewtwo', "yellow");

/*
We must tell hibernate the ids that have already been used.
The number must be larger than the last used id.
6 > 3 so we are good!
 */

alter sequence hibernate_sequence restart with 6;