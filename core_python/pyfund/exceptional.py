def convert(s):
    try:
        return int(s)
    except(ValueError, TypeError) as e:
        print("Conversion Error: {}".format(str(e)))
        return -1
