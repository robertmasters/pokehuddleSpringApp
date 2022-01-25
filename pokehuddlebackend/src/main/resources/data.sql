DELETE
FROM userroles;

DELETE
FROM roles;

DELETE
FROM articles;

DELETE
FROM preference;

DELETE
FROM users;

DELETE
FROM photos;

INSERT INTO users (userid, name, email, password, prefferenceid, photoid, articleid)
    VALUES (1, 'neo', 'neo@pikipika.com', 'pika123', 1,1,1),
           (2, 'Scarlett', 'scar@pikapika.com', 'pika123', 2,2,2);

INSERT INTO roles (roleid, name)
    VALUES (1, 'admin'),
           (2, 'member');

INSERT INTO photos (photoid, name, url)
    VALUES (1, 'pikachu', "no url"),
           (2, 'ashkool', "no url");

INSERT INTO articles (articleid, title, body, author)
    VALUES (1, 'Pikachu is too popular', 'Sic de isto et tutius perducit ad actum ipsum, ut si dico Ego autem vadam lavari, ut mens mea in statu naturae conformior. Et similiter circa alias', 'Neo' ),
           (2, 'Top Ash Mistakes', 'Sic de isto et tutius perducit ad actum ipsum, ut si dico Ego autem vadam', 'Neo'),
           (3, 'Mew vs Mewtwo', 'Sic de isto et tutius perducit ad actum ipsum, ut si dico “Ego autem vadam', 'Scarlett');

INSERT INTO userroles
    VALUES (1, 1),
           (2, 1),
           (2, 2);

INSERT INTO preference (prefferenceid, avatar, colorscheme)
    VALUES (4, 'pikachu', "maroon"),
           (5, 'mewtwo', "yellow");

/*
We must tell hibernate the ids that have already been used.
The number must be larger than the last used id.
15 > 11 so we are good!
 */

alter sequence hibernate_sequence restart with 15;