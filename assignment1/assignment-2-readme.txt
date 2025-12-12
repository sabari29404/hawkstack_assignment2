

---

#  Course Management System – REST API

A simple backend application built with **Spring Boot**, implementing essential e-learning features:

✔ Course CRUD
✔ User Enrollment
✔ Lesson Access (after enrollment)
✔ Course Rating (with idempotent update)

---

#  Features

### 1️ Course Management

Basic CRUD operations.

### 2️ User Enrollment

* Users can enroll in courses
* Prevent duplicate enrollment

### 3️ Lesson Access (Rule Applied)

**Only enrolled users can access lessons.**
If not enrolled → return message:

```
"User not enrolled for this course"
```

### 4️ Course Rating

* Only enrolled users can rate
* One rating per user per course
* Rating update allowed (idempotent)
* Fetch average rating of a course

---

#  API Endpoints

##  Courses

```
GET    /courses
GET    /courses/{id}
POST   /courses
PUT    /courses/{id}
DELETE /courses/{id}
```

---

##  Enrollment

### Enroll a user

```
POST /courses/{courseId}/enroll?userId=10
```

### Success output:

```
"User enrolled successfully"
```

---

##  Lesson Access

```
GET /courses/{courseId}/lessons?userId=10
```

### If enrolled:

```json
[
  { "id": 1, "title": "Lesson One" },
  { "id": 2, "title": "Lesson Two" }
]
```

### If NOT enrolled:

```
"User not enrolled for this course"
```

---

## ⭐ Rating API

### Submit/update rating

```
POST /courses/{courseId}/rating?userId=5&rating=4
```

Possible outputs:

```
"User must enroll before rating"
"Rating submitted successfully"
"Rating updated successfully (idempotent)"
```

---

### Get average rating

```
GET /courses/{courseId}/rating
```

Response:

```json
{
  "courseId": 1,
  "averageRating": 4.0
}
```

---

# 🧠 Rating Rules Summary

1. User must be enrolled
2. If rating exists → update it
3. If not → create new
4. Average rating = (sum of ratings) / total ratings

---

#  Tables Used

### courses

### users

### enrollments

### course_ratings

(With `UNIQUE(user_id, course_id)` for enrollments & ratings)

---

#  Testing Examples

### 1️ Not enrolled → cannot rate

```
POST /courses/1/rating?userId=3&rating=5
```

Output:

```
"User must enroll before rating"
```

### 2️ Enrolled → first rating

```
POST /courses/1/rating?userId=1&rating=5
```

### 3️ Update rating (idempotent)

```
POST /courses/1/rating?userId=1&rating=3
```

---

