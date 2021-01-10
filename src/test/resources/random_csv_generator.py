# This script is generating some random csv files for the TSP testcases.
# The distances between the cities are random so DON'T trust them :)
import random
import os

cities = ["Albany", "Amsterdam", "Auburn", "Babylon", "Batavia", "Beacon", "Bedford", "Binghamton", "Bronx", "Brooklyn", "Buffalo", "Chautauqua", "Cheektowaga", "Clinton", "Cohoes", "Coney Island", "Cooperstown", "Corning", "Cortland", "Crown Point", "Dunkirk", "East Aurora", "East Hampton", "Eastchester", "Elmira", "Flushing", "Forest Hills", "Fredonia", "Garden City", "Geneva", "Glens Falls", "Gloversville", "Great Neck", "Hammondsport", "Harlem", "Hempstead", "Herkimer", "Hudson", "Huntington", "Hyde Park", "Ilion", "Ithaca", "Jamestown", "Johnstown", "Kingston", "Lackawanna", "Lake Placid", "Levittown", "Lockport", "Mamaroneck", "Manhattan", "Massena", "Middletown", "Mineola", "Mount Vernon", "New Paltz", "New Rochelle", "New Windsor", "New York City", "Newburgh", "Niagara Falls", "North Hempstead", "Nyack", "Ogdensburg", "Olean", "Oneida", "Oneonta", "Ossining", "Oswego", "Oyster Bay", "Palmyra", "Peekskill", "Plattsburgh", "Port Washington", "Potsdam", "Poughkeepsie", "Queens", "Rensselaer", "Rochester", "Rome", "Rotterdam", "Rye", "Sag Harbor", "Saranac Lake", "Saratoga Springs", "Scarsdale", "Schenectady", "Seneca Falls", "Southampton", "Staten Island", "Stony Brook", "Stony Point", "Syracuse", "Tarrytown", "Ticonderoga", "Tonawanda", "Troy", "Utica", "Watertown", "Watervliet", "Watkins Glen", "West Seneca", "White Plains", "Woodstock", "Yonkers"]


for w in range(100):
    matrix = [["CSV"]]
    random.shuffle(cities)
    l = random.randint(3, 15)

    # add cities
    for x in range(l):
        matrix[0].append(cities[x])

    # add cities and fill with empty strings
    for x in range(l):
        temp = []
        temp.append(cities[x])
        for j in range(l):
            temp.append("")
        matrix.append(temp)

    # add values to matrix
    for i in range(1, len(matrix)):
        for x in range(len(matrix)-1, 0, -1):
            if x > i:
                matrix[i][x] = str(round(random.random()*100, 1))
            elif x == i:
                matrix[i][x] = "0"
            else:
                matrix[i][x] = matrix[x][i]

    # Write to file
    finalstring = []
    for x in matrix:
        finalstring.append(','.join(x))
    finalstring = '\n'.join(finalstring)
    filename = "zTSP_"+str(w)+".csv"
    f = open(filename, "w")
    f.write(finalstring)
    f.close()