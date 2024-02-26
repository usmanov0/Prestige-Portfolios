CREATE TABLE users (
        id SERIAL PRIMARY KEY,
        address VARCHAR(255)
);


CREATE TABLE blogs (
        id SERIAL PRIMARY KEY,
        title VARCHAR(100) NOT NULL,
        description VARCHAR(500) NOT NULL,
        body VARCHAR(5000) NOT NULL,
        like_count INTEGER DEFAULT 0,
        view_count INTEGER DEFAULT 0,
        date VARCHAR(255),
        unix_time BIGINT
);
CREATE INDEX title_ind ON blogs(title);
CREATE INDEX like_count_ind ON blogs(like_count);
CREATE INDEX unix_time_ind ON blogs(unix_time);


CREATE TABLE liked_blogs (
        user_id BIGINT,
        blog_id BIGINT,
        PRIMARY KEY (user_id, blog_id),
        FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
        FOREIGN KEY (blog_id) REFERENCES blogs(id) ON DELETE CASCADE
);


CREATE TABLE blog_user (
        blog_id BIGINT,
        user_id BIGINT,
        PRIMARY KEY (blog_id, user_id),
        FOREIGN KEY (blog_id) REFERENCES blogs(id) ON DELETE CASCADE,
        FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);


CREATE TABLE about_me (
        id SERIAL PRIMARY KEY,
        text VARCHAR(1000) NOT NULL
);


CREATE TABLE message (
        id SERIAL PRIMARY KEY,
        name VARCHAR(50) NOT NULL,
        contact_info VARCHAR(100) NOT NULL,
        body VARCHAR(1000) NOT NULL,
        date VARCHAR(255)
);


CREATE TABLE projects (
        id SERIAL PRIMARY KEY,
        name VARCHAR(30) NOT NULL,
        description VARCHAR(250) NOT NULL,
        start_date VARCHAR(10) NOT NULL,
        end_date VARCHAR(10) NOT NULL,
        date_order VARCHAR(14),
        link VARCHAR(250) NOT NULL
);
CREATE INDEX date_order_ind ON projects(date_order);


CREATE TABLE skills (
        id SERIAL PRIMARY KEY,
        name VARCHAR UNIQUE NOT NULL,
        type VARCHAR NOT NULL,
        simple_icons_icon_slug VARCHAR(50)
);


CREATE TABLE skill_project (
        skill_id INTEGER,
        project_id INTEGER,
        PRIMARY KEY (skill_id, project_id),
        FOREIGN KEY (skill_id) REFERENCES skills(id) ON DELETE CASCADE,
        FOREIGN KEY (project_id) REFERENCES projects(id) ON DELETE CASCADE
);


CREATE TABLE stats (
        id SMALLINT,
        date VARCHAR(255),
        views BIGINT
);
