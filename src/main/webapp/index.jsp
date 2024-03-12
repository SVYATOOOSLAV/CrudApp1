<html>
<head>
    <style>
        body {
            font-family: Arial, sans-serif;
            text-align: center;
        }

        h2 {
            color: blue;
        }

        form {
            display: inline-block;
            margin-top: 20px;
        }

        input[type="submit"] {
            padding: 10px 20px;
            background-color: #4285F4;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
        }

        input[type="submit"]:hover {
            background-color: #1A73E8;
        }
    </style>
</head>
<body>
<h2>Choose button</h2>
    <form method="get" action="/teachers">
        <input type="submit" value="Teachers"/>
    </form>
<br>
    <form method="get" action="/students">
        <input type="submit" value="Students"/>
    </form>
</body>
</html>
