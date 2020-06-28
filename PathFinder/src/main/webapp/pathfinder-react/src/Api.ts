export async function fetchCityInfo() {
  let url = "/PathFinderService/getAllCityInfo?format=json";
  try {
    const res = await fetch(url);
    const data = await res.json();
    console.log(data);
    return data;
  } catch (error) {
    console.error(error);
  }
}

export async function fetchShortestDistance(
  source: string,
  destination: string
) {
  let url = `/PathFinderService/getShortestDistance?sourceName=${source}&destinationName=${destination}&format=json`;
  try {
    const res = await fetch(url);
    const data = await res.json();
    return data;
  } catch (error) {
    console.error(error);
    return 0;
  }
}
