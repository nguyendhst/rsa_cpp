goog() {
    if [ -z "$1" ]; then
        return
    fi
    local query=$(echo "$1" | sed 's/ /+/g')
    carbonyl "https://www.google.com/search?q=$query"
}