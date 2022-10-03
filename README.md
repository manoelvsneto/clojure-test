# Usage

lein deps

lein run

Now you can visit [`localhost:3000`](http://localhost:3000) from your browser.

Create tables sql server


CREATE TABLE authors
(
id INT IDENTITY(1,1) PRIMARY KEY,
name varchar(150) not null,
description varchar(150) not null,
created_at DATETIME NULL
)

CREATE TABLE books
(
id INT IDENTITY(1,1) PRIMARY KEY,
title varchar(150) not null,
length INT NOT NULL,
genre varchar(150) not null,
author_id INT NOT NULL,
created_at DATETIME NULL
)

## API

- /authors

        GET => Get all authors
            Response => [
                {
                    "description": "This is a description",
                    "id": 1,
                    "name": "Example author",
                    "created_at": "2020-04-02T11:46:32Z"
                }
            ]

        POST => Create a new author
            Body => {
                "name": "Example author",
                "description": "This is a description"
            }

            Response => {
                "description": "This is a description",
                "id": 1,
                "name": "Example author",
                "created_at": "2020-04-02T11:46:32Z"
            }


- /authors/{authorId}

        GET => Get an author by id
            Response => {
                "description": "This is a description",
                "id": 1,
                "name": "Example author",
                "created_at": "2020-04-02T11:46:32Z"
            }
            

        PUT => Update an author
            Body => {
                "name": "Updated Example author",
                "description": "This is a description"
            }

            Response => {
                "description": "This is a description",
                "id": 1,
                "name": "Example author",
                "created_at": "2020-04-02T11:46:32Z"
            }
        
        DELETE => Delete an author by id
    
- /books/{bookId}

        GET => Get a book by bookId
            Response => {
				"author_id": 1,
				"genre": [
					"Indie Poptism"
				],
				"id": 1,
				"length": 180,
				"title": "good morning",
                "created_at": "2020-04-02T11:46:32Z"
			}
        
        PUT => Update a book by bookId
            Body => {
                "title": "updated book title",
                "genre": ["Indie Poptism"],
                "length": 180
            }

            Response => {
                "title": "updated book title",
                "genre": ["Indie Poptism"],
                "length": 180,
                "created_at": "2020-04-02T11:46:32Z"
            }
        
        DELETE => Delete a book by bookId

- /books/author/{authorId}

        GET => Get all books from an author by id
            Response => [
				{
					"author_id": 1,
					"genre": [
						"Indie Poptism"
					],
					"id": 1,
					"length": 180,
					"title": "good morning",
                    "created_at": "2020-04-02T11:46:32Z"
				}
            ]
