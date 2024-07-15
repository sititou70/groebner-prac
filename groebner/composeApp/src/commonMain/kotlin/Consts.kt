@JsExport
val sampleQuestions = """

// easy 1
// https://kakuyomu.jp/works/1177354054880542193/episodes/1177354054880624788

x - 1
y - 2
z - x - y

z - 3
y - 2
x - 1

---

// easy 2
// https://drillmu.net/?p=1834

a x + 5 y + 10
- 2 x + b y - 38
x + 5
y - 4

x + 5
y - 4
a - 6
b - 7

---

// easy 3
// https://drillmu.net/?p=1834

a x + b y + 6
b x - a y - 17
x - 2
y + 3

x - 2
y + 3
a - 3
b - 4

---

// easy 4
// https://drillmu.net/?p=1834

x + 2 y + 5
a x + b y - 26
b x - a y + 7
x + y + 1

x - 3
y + 4
a - 2
b + 5

---

// normal 1
// https://kakuyomu.jp/works/1177354054880542193/episodes/1177354054880624788

x^2 - 2
y^2 - 3
z - x - y

z^4 - 10 z^2 + 1
y + 0.5 z^3 - 5.5 z
x - 0.5 z^3 + 4.5 z

---

// normal 2
// https://www.lab2.toho-u.ac.jp/sci/is/tsuchiya/Introduction%20to%20Grobner%20basis.pdf

x^3 - 2 x y
x^2 y - 2 y^2 + x

y^3
x - 2 y^2

---

// hard 1
// https://www.lab2.toho-u.ac.jp/sci/is/tsuchiya/Introduction%20to%20Grobner%20basis.pdf

x y + z^2 - 2
x^2 - y z
x z - y^2

z^4 - 3 z^2 + 2
y z^2 - y
y^3 + z^3 - 2 z
x - y^2 z

"""
